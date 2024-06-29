function validate(){

    let otp = document.getElementById("otp").value;
    fetch('http://localhost:8080/user/register/validate?OTP='+otp,
        {
          method:'POST',
          headers:{
              'content-type':'application/json'
          }
        }
     ).then(
     response =>{
      if (response.ok) {
          console.log("ok");
      } 
      return response.json();
     }
     ).then(
      data=>{console.log(data)
        localStorage.setItem("AuthToken",data.data.token);
         window.location.href="home.html.html"
      }
    ).catch(error=>{console.log("error occured")})
}

function resend(){
    fetch('http://localhost:8080/user/register',
        {method:'POST',
          headers:{
              'content-type':'application/json'
          },
          body:sessionStorage.getItem("Ama_email")
        }
     ).then(
     response =>{
      if (response.ok) {
          window.location.href="registerOTP.html";
      } else if(response.status == 409){
          window.alert("duplicate data found");
      }
      return response.json();
     }
     ).then(
      data=>{console.log(data)}
  ).catch(error=>{console.log("error occured")})
}