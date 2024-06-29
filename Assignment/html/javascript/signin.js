

   window.onload=function(){document.getElementById("cMail").innerText=sessionStorage.getItem("signinEmail");
   }

  function signin(){
    let mail = sessionStorage.getItem("signinEmail");
    let pass = document.getElementById("pass").value;
     console.log(pass)
    if(pass==''){
      window.alert("enter password");
      return;
    }


    fetch('http://localhost:8080/user/signin?Email='+mail+'&password='+pass,
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
         sessionStorage.setItem("AuthToken",data.data.token);
         window.location.href="home.html.html"
      }
   ).catch(error=>(console.log(error)))}