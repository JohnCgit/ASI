var port = 8083;
// Permet de rediriger le client en fonction du résultat de la requête /login/Surname/Password
// - authentification réussi : /index.html?Surname=Surname&Password=Password
// - sinon : /login.html
function setActionLogin(form) {

    var Name = document.getElementById("Name").value;
    var Password = document.getElementById("Password").value;
    form.method = "get";
    if (loadRessource(`http://127.0.0.1:${port}/login/${Name}/${Password}`,"POST") == "true") {

        form.action = "index.html"; // /index.html?Surname=Surname&Password=Password
    } else {
        alert("Invalid Surname and/or Password");
        form.action = "login.html";
    }
}

// Permet de rediriger le client en fonction du résultat de la requête /user/create/Name/Surname/Password
// - authentification réussi : /index.html?Surname=Surname&Password=Password
// - sinon : /sub.html
function setActionSub(form) {

    var Name = document.getElementById("Name").value;
    var Surname = document.getElementById("Surname").value;
    var Password = document.getElementById("Password").value;
    var RePassword = document.getElementById("Re-Password").value;
    form.method = "get";
    if (Password == RePassword) {
        if (loadRessource(`http://127.0.0.1:${port}/user/create/${Name}/${Surname}/${Password}`,"POST") == "true") {
            form.action = "index.html";
        } else {
            alert("Surname already used");
            form.action = "sub.html";
        }
    } else {
        alert("Passwords do not match");
        form.action = "sub.html";
    }
}

function loadRessource(source, method) {
    var xhttp = new XMLHttpRequest();
    xhttp.open(method, source, false);
    xhttp.overrideMimeType("application/json");
    xhttp.send();
    if (xhttp.status == 200) {
        return xhttp.response;
    }
};