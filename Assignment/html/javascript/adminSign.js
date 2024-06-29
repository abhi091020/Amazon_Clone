
let email;
function set(){
   email=document.getElementById("mail").value;
    if (email == '') {
       window.alert("enter mail")
       return;
    }
    
    document.getElementById("sign-page").style.display="none";
    document.getElementById("pass-page").style.display="block";
   
}


//password verification

function signIn(){
   let pass = document.getElementById("pass").value;
    
   if(pass==''){
     window.alert("enter password");
     return;
   }


   fetch('http://localhost:8080/admin/signin?Email='+email+'&password='+pass,
     {
        method:'POST',
       headers:{
           'content-type':'application/json'
       }
     }
  ).then(response => {
     if (response.ok) {
        return response.json()
     }
          
  }).then(
     data=>{
      if (document.getElementById("keep-signed").checked) {
         localStorage.setItem("AdminToken",data.data.token);
      }
        sessionStorage.setItem("AdminToken",data.data.token);
        window.location.href="admin.html"
     }
  ).catch(error=>(console.log(error)))}