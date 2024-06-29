window.onload=function(){
    let token = localStorage.getItem("AdminToken");

    if(token != '' || token != null){
        sessionStorage.setItem("AdminToken",token);
    }
}

window.onload=function(){
    if(localStorage.getItem('AdminToken')==null){
        document.getElementById('signin').style.display='block';
        document.getElementById('logout').style.display='none'
    }
}

function logout(){
    localStorage.removeItem('AdminToken')
    window.location.reload()
}

function signin(){
    if(localStorage.getItem('AdminToken')!=null){
        window.alert("already sign in")
        return
    }
    window.location.href="adminSignin.html"
}