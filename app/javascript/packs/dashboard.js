

function setActive() {
  const menuItems = document.getElementById('menu-list').querySelectorAll("a")
  let url = window.location.href;
  console.log(menuItems)
  menuItems.forEach(el => {
    if (el.href === url){
      el.closest('.menu-item').classList.add('active');
    };
    if (window.location.pathname === "/"){
      document.querySelector(".link-import").classList.add('active');
    };
  });
}

window.onload = setActive()