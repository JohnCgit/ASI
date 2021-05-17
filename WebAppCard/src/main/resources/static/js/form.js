// Permet de rediriger le client en fonction du résultat de la requête /login/Surname/Password
// - authentification réussi : /index.html?Surname=Surname&Password=Password
// - sinon : /login.html
function setActionLogin(form) {

    var Surname = document.getElementById("Surname").value;
    var Password = document.getElementById("Password").value;
    //TODO : change test with url : /login/Surname/Password
    if (loadRessource(`http://127.0.0.1:8083/login/${Surname}/${Password}`),"POST") {
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

    if (Password == RePassword) {
        if (Surname == "test") {
            //TODO : change test with url : /register/Name/Surname/Password
            form.action = "index.html"; // /index.html?Surname=Surname&Password=Password
        } else {
            alert("Surname already used");
            form.action = "sub.html";
        }
    } else {
        alert("Passwords do not match");
        form.action = "sub.html";
    }
}

// Fonction permettant la récupération d'une information située à l'url source avec la méthode method
function loadRessource(source, method) {
    var xhttp = new XMLHttpRequest();
    xhttp.open(method, source, false);
    xhttp.overrideMimeType("application/json");
    xhttp.send();
    if (xhttp.status == 200) {
        return xhttp.response;
    }
};