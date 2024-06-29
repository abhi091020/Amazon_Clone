function validate(){

    let otp = document.getElementById("otp").value;
    fetch('http://localhost:8080/admin/register/validate?OTP='+otp,
        {method:'POST',
          headers:{
              'content-type':'application/json'
          }
        }
     ).then(
     response =>{
      if (response.ok) {
          console.log(response);
      } else {
          
      }
      return response.json();
     }
     ).then(
      data=>{
        console.log(data)
        console.log(data.data.token)
        sessionStorage.setItem("AdminToken",data.data.token)
        window.location.href="admin.html";
      }
    ).catch(error=>{console.log("error occured")})
}

function resend(){
    fetch('http://localhost:8080/admin/register',
        {method:'POST',
          headers:{
              'content-type':'application/json'
          },
          body:sessionStorage.getItem("adminName")
        }
     ).then(
     response =>{
      if (response.ok) {
          window.location.href="adminROTP.html";
      } else if(response.status == 409){
          window.alert("duplicate data found");
      }
      return response.json();
     }
     ).then(
      data=>{console.log(data)}
  ).catch(error=>{console.log("error occured")})
}