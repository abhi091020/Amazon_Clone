const menuIcon = document.querySelector(".menu-icon");
const backdrop = document.querySelector(".backdrop");
const navLinks = document.querySelector(".nav-links");
const closeIcon = document.querySelector(".close-icon");

menuIcon.addEventListener("click", () => {
  backdrop.classList.add("active");
  navLinks.classList.add("active");
});

closeIcon.addEventListener("click", () => {
  backdrop.classList.remove("active");
  navLinks.classList.remove("active");
});

backdrop.addEventListener("click", () => {
  backdrop.classList.remove("active");
  navLinks.classList.remove("active");
});


function isNull(element){
  if (element !=null) {
    return element.path;
  }
}

window.onload=function(){
  let data = JSON.parse(sessionStorage.getItem("products"));

let element=data.data.find(obj=>(obj.id==sessionStorage.getItem("productId")));

sessionStorage.setItem("cartEle",JSON.stringify(element));

let img=document.getElementById('main-image');
img.src="http://localhost:8080/"+isNull(element.subImages[0]);
 document.getElementById("product-img3").src="http://localhost:8080/"+isNull(element.subImages[1]);
 document.getElementById("product-img2").src="http://localhost:8080/"+isNull(element.subImages[0]);
document.getElementById("product-img4").src="http://localhost:8080/"+isNull(element.subImages[3]);
document.getElementById("product-img1").src="http://localhost:8080/"+isNull(element.subImages[2]);

document.getElementById("product-name").innerText=element.productName;
document.getElementById("product-desc").innerText=element.shortDesc;
document.getElementById("price").innerText=element.prize;
document.getElementById("dis").innerText=element.discount;
document.getElementById("prev-price").innerText=element.costPrize+(element.costPrize*(element.discount/100));



data.data.forEach(element => {
  let mainFrame = document.getElementById("mainFrame");

let card=  `<div class="col hp" onclick="redirect(${element.id})">
<div class="card h-100 shadow-sm">
  <a>
    <img src="http://localhost:8080/${isNull(element.subImages[1])}" class="card-img-top" alt="product.title" />
  </a>

  <div class="label-top shadow-sm">
    <a class="text-white" >${element.brand}</a>
  </div>
  <div class="card-body">
    <div class="clearfix mb-3">
      <span class="float-start badge rounded-pill bg-success">${element.prize}</span>

      <span class="float-end"><a class="small text-muted text-uppercase aff-link">reviews</a></span>
    </div>
    <h5 class="card-title">
      <a target="_blank" >${element.shortDesc}</a>
    </h5>

    <div class="clearfix mb-1">

      <span class="float-start"><a><i class="fas fa-question-circle"></i></a></span>

      <span class="float-end">
        <i class="far fa-heart" style="cursor: pointer"></i>

      </span>
    </div>
  </div>
</div>
</div>`;
  mainFrame.innerHTML +=card;
});


}

function redirect(element){
  sessionStorage.setItem("productId",element);
  window.location.reload()
}



//add cart
function addCart() {
  let token=localStorage.getItem("AuthToken");
  if (token == null) {
    window.alert("signin");
    return;
  } 
  let count=document.getElementById("count").innerText;
  if(count==0){
    window.alert("add numbers");
    return;
  }

  sessionStorage.setItem("cardData",JSON.stringify({
    "user":localStorage.getItem("AuthToken"),
    "product":JSON.parse(sessionStorage.getItem("cartEle")).id,
    "quantity":count
}))
  

window.location.href="checkout_page/checkout.html";




  }

