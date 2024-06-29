console.log("done");
function register(){
    let email = document.getElementById('email').value;
    let name = document.getElementById("name").value;
    let phone = document.getElementById("phone").value;
    let pass = document.getElementById("pass").value;

    
    
    fetch('http://localhost:8080/user/register',
              {
                method:'POST',
                headers:{
                    'content-type':'application/json'
                },
                body:JSON.stringify({
                    "userName":name,
                    "userEmail":email,
                    "userPhone":phone,
                    "password":pass
                })
              }
           ).then(
           response =>{
            if (response.ok) {
                window.location.href="registerOTP.html";
                sessionStorage.setItem("Ama_email",
                    JSON.stringify({"userName":name,
                    "userEmail":email,
                    "userPhone":phone,
                    "password":pass}));
            } 
            else if(response.status == 409){
                window.alert("duplicate data found");
            }
            return response.json();
           }
           ).then(
            data=>{console.log(data)}
        ).catch(error=>{console.log("error occured")})

}