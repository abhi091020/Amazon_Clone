function signIn(){
    let mail = sessionStorage.getItem("adminMail");
    let pass = document.getElementById("pass").value;
     console.log(pass)
    if(pass==''){
      window.alert("enter password");
      return;
    }


    fetch('http://localhost:8080/admin/signin?Email='+mail+'&password='+pass,
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
      else{
         window.alert("error")
      }
      
   }).then(
      data=>{
         sessionStorage.setItem("AdminToken",data.data.token);
         window.location.href="admin.html"
      }
   ).catch(error=>(console.log(error)))}