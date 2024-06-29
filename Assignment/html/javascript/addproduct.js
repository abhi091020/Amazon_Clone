
if((sessionStorage.getItem("AdminToken") == null) && (localStorage.getItem("AdminToken") != null))
{
    sessionStorage.setItem("AdminToken",localStorage.getItem("AdminToken"));
}

//add multiple image inputs and remove it
const i=1;
function addimage()
{
let Cd1 = document.getElementById("Cd1");
Cd1.innerHTML +=`<div class="mb-3" id="formFile${i}">
                        <label for="images" class="form-label">add Image</label>
                        <input class="images" type="file" >
                        <label onclick="remove('formFile${i}')">remove</label>
                      </div>`;

}

function remove(id){
    document.getElementById(id).remove();
}





// for adding images
let productId;
//add data into database
function add(){
let token = sessionStorage.getItem("AdminToken");
console.log(token)
if (token == null) {
  window.alert("Signin");
  return;
}
  
// Function to fetch data from an element by ID
function fetchDataAndValidate(id) {
  let element = document.getElementById(id);
  if (element) {
      let data = element.value.trim(); // Assuming we're fetching input values
      if (!data) {
          element.style.outline = '2px solid red'; // Apply red outline if data is absent
      } else {
          element.style.outline = ''; // Remove outline if data is present
      }
      return data; // Return the fetched data
  } else {
      console.error(`Element with ID '${id}' not found.`);
      return null; // Return null if element is not found
  }
}

// Function to fetch and save data for all required elements

  // List of IDs to fetch data from
  let ids = [
      'productName',
      'category',
      'subCategory',
      'brand',
      'shortDesc',
      'longDesc',
      'prize',
      'discount',
      'costPrize',
      'offers',
      'stock',
      'delTime'
  ];

  let data = {};

  let flag=false;
  ids.forEach(id => {
      let fetchedData = fetchDataAndValidate(id);
      if (fetchedData !== null && fetchedData !='') {
          data[id] = fetchedData;
      }else{
        flag=true;
      }
  });

  if(flag){
    return;
  }
  
  fetch('http://localhost:8080/product/add?token='+sessionStorage.getItem("AdminToken"),
    {
      method:'POST',
      headers:{
          'content-type':'application/json'
      },
      body:JSON.stringify(data)
    }
 ).then(
 response =>{
  if (response.ok) {

        window.alert("product added succesfully");
        document.getElementById("data").style.display="none";
        document.getElementById("images").style.display="block";

  } else if(response.status == 409){
      window.alert("duplicate data found");
  }
  return response.json();
 }
 ).then(
  data=>{productId=data.data.id}
).catch(error=>{console.log("error occured")})
}



//adding images to database
function addI(){
let Files = document.querySelectorAll('.images')
let data = new FormData();

Files.forEach((input,index)=>{data.append("files",input.files[0])})
console.log(data.get("files")[0])

for(const[key,value] of data.entries()){
  console.log(value.name)
}

fetch('http://localhost:8080/product/add/image?pid='+productId,
  {
    method:'POST',
    body:data
  }
).then(
response =>{
if (response.ok) {
     window.alert("images added succesfully");
     window.location.reload();
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

