function check(){
    let email= document.getElementById('email').value;
   if(email != ""){
    fetch('http://localhost:8080/user/signin/forgot/password?Email='+email,
        {method:'POST',
          headers:{
              'content-type':'application/json'
          }
          
        }
     ).then(
     response =>{
      if (response.ok) {
          window.location.href="forgotOTP.html";
      } else if(response.status == 404){
          window.alert("no user found");
      }
      return response.json();
     }
     ).then(
      data=>{console.log(data)}
  ).catch(error=>{console.log("error occured")})
  }else{
      document.getElementById('email').style.outline="2px solid red";
  }
}

function check2(){
    let otp= document.getElementById('otp').value;
    let pass= document.getElementById('pass').value;

    if (pass!="") {
        if (otp!="") {
            
                fetch('http://localhost:8080/user/signin/validate?OTP='+otp+"&password="+pass,
                    {
                        method:'POST',
                      headers:{
                          'content-type':'application/json'
                      }
                      
                    }
                 ).then(
                 response =>{
                  if (response.ok) {
                    window.alert("successful");
                    
                  } else if(response.status == 403){
                      window.alert("wrong otp enterd");
                  }
                  return response.json();
                 }
                 ).then(data=>{
                    sessionStorage.setItem("AuthToken",data.data.token);
                    window.location.href="home.html.html"
                 })


        } else {
            document.getElementById('otp').style.outline="2px solid red";
        } 
    } else {
        document.getElementById('pass').style.outline="2px solid red";
    }
}