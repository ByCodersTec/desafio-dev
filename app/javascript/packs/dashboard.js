

function setActive() {
  const menuItems = document.getElementById('menu-list').querySelectorAll("a")
  let url = window.location.href;
  console.log(menuItems)
  menuItems.forEach(el => {
    if (window.location.pathname.includes("/finance_reports/new")){
      document.querySelector(".link-import").classList.add('active');
    }else {
      document.querySelector(".link-reports").classList.add('active');
    };
  });
}

window.onload = setActive()