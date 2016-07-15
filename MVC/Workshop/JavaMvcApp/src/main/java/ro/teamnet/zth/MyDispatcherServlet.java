package ro.teamnet.zth;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestParam;
import ro.teamnet.zth.fmk.AnnotationScanUtils;
import ro.teamnet.zth.fmk.MethodAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 7/14/2016.
 */
public class MyDispatcherServlet extends HttpServlet {

    /*
       key = urlPath
       value = informatii despre meroda acre va procesa acest url
     */
    Map<String, MethodAttributes> registru = new HashMap<String, MethodAttributes>();

    @Override
    public void init() throws ServletException {
        try {
            /* Cautare clase din pachet */
            Iterable<Class> controllers = AnnotationScanUtils.getClasses("ro.teamnet.zth.appl.controller");
            for (Class controller : controllers) {
                if (controller.isAnnotationPresent(MyController.class)) {
                    MyController controllerAnnotation = (MyController)controller.getAnnotation(MyController.class);
                    String controllerUrlPath = controllerAnnotation.urlPath();
                    Method[] controllerMethods = controller.getMethods();
                    for (Method controllerMethod : controllerMethods) {
                        if (controllerMethod.isAnnotationPresent(MyRequestMethod.class)) {
                            MyRequestMethod methodAnnotation = controllerMethod.getAnnotation(MyRequestMethod.class);
                            String methodUrlPath = methodAnnotation.urlPath();
                            /* Construiesc cheia pt registru */
                            String urlPath = controllerUrlPath + methodUrlPath;
                            /* Contruiesc valoarea */
                            MethodAttributes methodAttributes = new MethodAttributes();
                            methodAttributes.setControllerClass(controller.getName());
                            methodAttributes.setMethodType(methodAnnotation.methodType());
                            methodAttributes.setMethodName(controllerMethod.getName());
                            methodAttributes.setParameterTypes(controllerMethod.getParameterTypes());
                            /* Adauga inregistrarea in registru */
                            registru.put(urlPath, methodAttributes);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public Object dispatch(HttpServletRequest req, HttpServletResponse resp) {

        /* Se obtine informatia din URL care apare dupa .../app/mvc */
        String pathInfo = req.getPathInfo();
        MethodAttributes methodAttributes = registru.get(pathInfo);
        if (methodAttributes == null) //URL-ul nu poate fi procesat;
            return "E null!";

        /* Obtinerea parametrului din browser */
        //String parameter = req.getParameter("id");

        String controllerName = methodAttributes.getControllerClass();
        try {
            Class <?> controllerClass = Class.forName(controllerName);
            try {
                Object controllerInstance = controllerClass.newInstance();
                try {
                    Method method = controllerClass.getMethod(methodAttributes.getMethodName(), methodAttributes.getParameterTypes());
                    Parameter[] parameters = method.getParameters();
                    List<Object> parameterValues = new ArrayList<>();
                    for (Parameter parameterI : parameters) {
                        if (parameterI.isAnnotationPresent(MyRequestParam.class)) {
                            MyRequestParam annotation = parameterI.getAnnotation(MyRequestParam.class);
                            String name = annotation.name();
                            String requestParamValue = req.getParameter(name);
                            Class<?> type = parameterI.getType();
                            Object requestParamObject = new ObjectMapper().readValue(requestParamValue, type);
                            parameterValues.add(requestParamObject);
                        }
                    }
                    try {
                        //Long id = Long.valueOf(parameter);
                        Object r = method.invoke(controllerInstance, parameterValues.toArray());
                        return r;
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (JsonParseException e) {
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        /*if (pathInfo.startsWith("/employees")) {
            EmployeeController employeeController = new EmployeeController();
            String result = employeeController.getAllEmployees();
            return result;
        }
        else if (pathInfo.startsWith("/departments")) {
            DepartmentController departmentController = new DepartmentController();
            String result = departmentController.getAllDepartments();
            return result;
        }*/

        return "***";
    }

    public void reply(Object r, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String res = objectMapper.writeValueAsString(r);

        PrintWriter out = resp.getWriter();
        out.printf(res);
    }

    public void sendExceptionError(Exception e, HttpServletRequest req, HttpServletResponse resp) {

    }

    public void dispatchReply(String identificator, HttpServletRequest req, HttpServletResponse resp) {
        Object r = null;
        try {
            /** DISPATCH **/
            r = dispatch(req, resp);
        }
        catch (Exception e) {
            /** ERROR **/
            sendExceptionError(e, req, resp);
        }

        /** REPLY **/
        try {
            reply(r, req, resp);
        } catch (IOException e) {
            sendExceptionError(e, req, resp);
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //instructiuni de delegare
        dispatchReply("GET", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //instructiuni de delegare
        dispatchReply("POST", req, resp);
    }

}
