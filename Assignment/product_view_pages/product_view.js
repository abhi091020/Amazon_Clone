let cat = sessionStorage.getItem('cat');

let object=fetch('http://localhost:8080/product/get/subCategory?cat='+cat,
    {
        method:'GET'
     
    }
 ).then(
 response =>{
  if (!response.ok) {
   window.alert("Product Not Added.Please Add The Products")
  } 
  return response.json()
 }
 ).then
 (data =>
    {
      console.log(data)
       load(data)
    }).catch(error=>console.log(error))


    

    function load(dat){
      sessionStorage.setItem("products",JSON.stringify(dat))
      dat.data.forEach(element => {
        console.log(element)
        let mainFrame = document.getElementById("mainFrame");

      let card=  `<div class="col hp" onclick="redirect(${element.id})">
      <div class="card h-100 shadow-sm">
        <a>
          <img src="http://localhost:8080/${isNull(element.subImages[0])}" class="card-img-top" alt="product.title" />
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
        window.location.href="Detail_Page/detail.html";
    }

    function isNull(element){
      if (element !=null) {
        return element.path;
      }
    }

    