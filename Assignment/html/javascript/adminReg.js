
function register(){
    let email = document.getElementById('email').value;
    let name = document.getElementById("name").value;
    let phone = document.getElementById("phone").value;
    let pass = document.getElementById("pass").value;
    let add = document.getElementById("address").value;

    
    let nameX = /^[a-zA-z]{2,30}$/;
    let addX = /^[a-zA-z]{2,30}$/;
    let emailX =/^[a-z]+@.[a-z]$/;
    let phoneX = /^[978][0-9]{9}$/;
    let passX = /^[a-zA-z0-9]{6,}$/;
    
    
    fetch('http://localhost:8080/admin/register',
              {method:'POST',
                headers:{
                    'content-type':'application/json'
                },
                body:JSON.stringify({"adminName":name,
                    "adminEmail":email,
                    "adminPhone":phone,
                    "password":pass,
                    "address":add
                })
              }
           ).then(
           response =>{
            if (response.ok) {
                window.location.href="adminROTP.html";
                sessionStorage.setItem("Admin_email",JSON.stringify({"adminName":name,
                    "adminEmail":email,
                    "adminPhone":phone,
                    "password":pass,
                    "address":add
                }));
            } else if(response.status == 409){
                window.alert("duplicate data found");
            }
            return response.json();
           }
           ).then(
            data=>{console.log(data)}
        ).catch(error=>{console.log("error occured")})

}