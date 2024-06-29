window.onload=function(){

    let product={
        "productName": "Mobile Phone H",
        "category": "Electronics",
        "subCategory": "Mobile",
        "brand": "TechSavvy S",
        "shortDesc": "6GB RAM | 256GB Storage | Hexa-core processor",
        "longDesc": "Get a balance of performance and storage with this mobile phone featuring a hexa-core processor, 6GB of RAM, and 256GB of storage.",
        "prize": 0.0,
        "discount": 10.0,
        "costPrize": 0.0,
        "offers": "Summer Clearance Sale",
        "stock": 18,
        "delTime": 0,
        
    }
  able(true,product);
    
}

function able(val,product){
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
  
  
    ids.forEach(id => {
        let element=document.getElementById(id);
        element.value=product[id];
        element.disabled=val;
    });

    for (let index = 0; index < product.subImage.length; index++) {
        document.getElementById("image-remove").innerHTML+=` <div class="card">
                  <img src="${product.subImage[index].path}" class="card-img-top" alt="..." width="250" height="250">
                  <input type="checkbox" name="" id="${product.subImage[index]}">
                </div>
                
                `
    }
}

let product={
    "productName": "Mobile Phone H",
    "category": "Electronics",
    "subCategory": "Mobile",
    "brand": "TechSavvy S",
    "shortDesc": "6GB RAM | 256GB Storage | Hexa-core processor",
    "longDesc": "Get a balance of performance and storage with this mobile phone featuring a hexa-core processor, 6GB of RAM, and 256GB of storage.",
    "prize": 0.0,
    "discount": 10.0,
    "costPrize": 0.0,
    "offers": "Summer Clearance Sale",
    "stock": 18,
    "delTime": 0,
    
}

function modify(){
    able(false,product);
}