


function getPro(id){
    fetch('http://localhost:8080/product/get/id?id='+id,
        {method:'GET',
          headers:{
              'content-type':'application/json'
          }
        }
     ).then(
     response =>{
      if (!response.ok) {
          window.alert("fuck")
      } 
      return response.json();
     }
     ).then(
      data=>{
           window.location.href="modify.html";
          sessionStorage.setItem("ProductV",data.data);
      }
    ).catch(error=>{console.log(error)})
}


window.onload=function(){
    fetch('http://localhost:8080/product/get/all',
        {method:'GET',
          headers:{
              'content-type':'application/json'
          }
        }
     ).then(
     response =>{
      if (!response.ok) {
          window.alert("fuck")
      } 
      return response.json();
     }
     ).then(
      data=>{
        console.log(data)
         getLoaded(data.data);
      }
    ).catch(error=>{console.log(error)})
}

function getLoaded(data){
data.forEach(element => {
    document.getElementById('main').innerHTML+=`<div class="row g-0" id=${element.id}>
              <div class="col-md-4">
                <img src=http://localhost:8080/"${isNull(element.subImages[0])}" class="img-fluid rounded-start" alt="..." width="200" height="200">
              </div>
              <div class="col-md-8">
                <div class="card-body">
                  <h5 class="card-title" id="name">${element.productName}</h5>
                  <p class="card-text" id="desc">${element.shortDesc}</p>
                  <button type="button" class="btn btn-warning" id="view" onclick="getPro()">View</button>
                  <button type="button" class="btn btn-warning" id="remove" onclick="remove(${element.id})">Remove</button>
                </div>
               
              </div>
            </div>`
});
}

function remove(id){
    fetch('http://localhost:8080/product/remove?id='+id,
        {method:'POST',
          headers:{
              'content-type':'application/json'
          }
        }
     ).then(
     response =>{
      if (!response.ok) {
          window.alert("removed successfully")
          document.getElementById(id).innerHTML=''
      } 
      return response.json();
     }
     ).then(
      data=>{
        console.log(data)
         getLoaded(data.data);
      }
    ).catch(error=>{console.log(error)})
}

function isNull(element){
  if (element !=null) {
    return element.path;
  }
}
