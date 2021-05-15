function setActionLogin(form){

    var Surname = document.getElementById("Surname").value;
    var Password = document.getElementById("Password").value;
    //TODO : change test with url : /login/Surname/Password
    if(Surname == "test"){
        form.action = "index.html"; // /index.html?Surname=Surname&Password=Password
    }
    else {
        alert("Invalid Surname and/or Password");
        form.action = "login.html";
    }
}

function setActionSub(form){
    var Name = document.getElementById("Name").value;
    var Surname = document.getElementById("Surname").value;
    var Password = document.getElementById("Password").value;
    var RePassword = document.getElementById("Re-Password").value;
    if(Password == RePassword){
        if( Surname == "test"){
        //TODO : change test with url : /register/Name/Surname/Password
            form.action = "index.html"; // /index.html?Surname=Surname&Password=Password
        }
        else {
            alert("Surname already used");
            form.action = "sub.html";
        }   
    }
    else{
        alert("Passwords do not match");
        form.action = "sub.html";
    }
}