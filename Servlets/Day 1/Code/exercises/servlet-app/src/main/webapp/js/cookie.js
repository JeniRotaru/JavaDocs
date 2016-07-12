(function () {
    var today = new Date();
    var expiry = new Date(today.getTime() + 30 * 24 * 3600 * 1000); // plus 30 days

    window.addEventListener('load', initialize, false)

    function initialize() {
        document.getElementById('submitButton').addEventListener('click', setCookie("cookie", "ZTH"));
    };

    function setCookie(name, value) {
        document.cookie = name + "=" + escape(value) + "; path=/; expires=" + expiry.toGMTString();
    };
})();