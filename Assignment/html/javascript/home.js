const imgs = document.querySelectorAll('.header-slider ul img');
const control_prev = document.querySelector('.control-prev');
const control_next =  document.querySelector('.control-next');

let n = 0;

function changeSlide(){
    for (let i = 0; i< imgs.length; i++) {
      imgs[i].style.display = 'none';
    }
    imgs[n].style.display = 'block';
}
changeSlide();

control_prev.addEventListener('click', (e) => {
    if(n > 0){
        n--;
    }
    else{
        n = imgs.length - 1;
    }
    changeSlide();
})

control_next.addEventListener('click', (e) => {
    if(n < imgs.length - 1){
        n++;
    } 
    else{
        n = 0;
    }
    changeSlide();
})

const scrollContainer = document.querySelectorAll('.products');
for (const item of scrollContainer) {
    item.addEventListener('wheel',(evt)=>{
        evt.preventDefault();
        item.scrollLeft += evt.deltaY;
    })
}


//credirect

function explore(category){
  sessionStorage.setItem('cat',category);
  window.location.href="/product_view_pages/product_view.html";
//   /product_view_pages/product_view.html
}

function signin(){
   if(localStorage.getItem("AuthToken") == null){
     window.location.href='Signin.html'
   }
   else{
    window.alert("already signin");
   }
}

window.onload=function(){
    if(localStorage.getItem('AuthToken')==null){
        document.getElementById('logout').style.display='none';
    }
}

function logout(){
    localStorage.removeItem('AuthToken')
    console.log(localStorage.getItem('AuthToken'))
    window.location.reload()
}
