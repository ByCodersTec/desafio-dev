const dropZone = document.querySelector("#drag-zone")
const bodyZone = document.querySelector("body")

let file;

dropZone.addEventListener("dragover", (event) => {
  event.preventDefault();
  dropZone.classList.add("active")
});

dropZone.addEventListener("dragleave", () => {
  dropZone.classList.remove("active")
});

dropZone.addEventListener("drop", (event) => {
  event.preventDefault();
  let inputFile = document.querySelector("#finance_file") 
  file = event.dataTransfer.files;

  if (file.length > 1){
    alert("Insira apenas um arquivo por vez")
    return false
  }
  
  if (isSupported(file[0])){
    inputFile.files = file
  };

});

document.querySelector("#finance_file").addEventListener("change", (event) => {
  file = event.target.files[0]
  if (!isSupported(file)){
    const fileNameField = document.querySelector("#drag-zone p").innerText = "Arraste um arquivo"
    const fileSizeField = document.querySelector("#drag-zone span").innerText = "ou"
    event.target.value = ""
  };
});

function isSupported(file) {
  let fileType = file.type;
  let supportedType = ["text/plain"]
  if (supportedType.includes(fileType)){
    let fileSize = (file.size / 1024).toFixed(2)
    let fileName = file.name 
    const fileNameField = document.querySelector("#drag-zone p").innerText = fileName
    const fileSizeField = document.querySelector("#drag-zone span").innerText = fileSize + "KB"
    return true
  } else {
    alert("Apenas arquivos TXT são permitidos, por gentileza, tente novamente")
    return false
  }
};
