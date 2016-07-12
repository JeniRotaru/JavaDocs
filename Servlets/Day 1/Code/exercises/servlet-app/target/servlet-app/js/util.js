(function () {
    'use strict';

    window.addEventListener('load', initialize, false);

    //######################

    function initialize() {
        var userInput = document.getElementById('user');
        addSubmitListener(userInput);
    };

    function addSubmitListener(elem) {
        elem.addEventListener("keypress", submissionCallback);
    };

    function submissionCallback(event) {
        if (event.which == 13) {
            event.preventDefault();
            document.getElementById("form1").submit();
        }
    };

})();
