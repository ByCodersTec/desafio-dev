

function setActive() {
  const menuItems = document.getElementById('menu-list').querySelectorAll("a")
  let url = window.location.href;
  menuItems.forEach(el => {
    if (window.location.pathname.includes("/finance_reports/new") || window.location.pathname === "/"){
      document.querySelector(".link-import").classList.add('active');
    }else {
      document.querySelector(".link-reports").classList.add('active');
    };
  });
}

window.onload = setActive()