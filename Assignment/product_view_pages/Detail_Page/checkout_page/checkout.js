window.onload=function(){
    let element = JSON.parse(sessionStorage.getItem("cartEle"));
let data = JSON.parse(sessionStorage.getItem("cardData"));

document.getElementById("count").innerText="Items ("+data.quantity+")";
document.getElementById("total").innerText=(element.prize*data.quantity);
document.getElementById("final").innerText=(element.prize*data.quantity);
document.getElementById("image").src="http://localhost:8080/"+element.subImages[1].path;
}


function place(){
    let data = JSON.parse(sessionStorage.getItem("cardData"));
    fetch('http://localhost:8080/order/add',
        {method:'POST',
          headers:{
              'content-type':'application/json'
          },
          body:JSON.stringify({
            "user":sessionStorage.getItem("AuthToken"),
            "products":JSON.parse(sessionStorage.getItem("cartEle")).id,
            "quantity":data.quantity,
            "address":"defaualt address"
        })
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
      data=>{console.log(data)
        window.location.href="payment/paymentsuccess.html";
      }
    ).catch(error=>{console.log(error)})
}