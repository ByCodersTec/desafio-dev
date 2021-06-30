function _getElementById(el) {
    return document.getElementById(el);
}

function uploadFile(url) {
    var file = _getElementById("cnab_file").files[0];
    var formdata = new FormData();
    formdata.append("cnab_file", file);
    formdata.append("csrfmiddlewaretoken", document.getElementsByName('csrfmiddlewaretoken')[0].value);
    var ajax = new XMLHttpRequest();
    ajax.upload.addEventListener("progress", progressHandler, false);
    ajax.addEventListener("load", completeHandler, false);
    ajax.addEventListener("error", errorHandler, false);
    ajax.addEventListener("abort", abortHandler, false);
    ajax.open("POST", url);
    ajax.send(formdata);
}

function progressHandler(event) {
    _getElementById("loaded_n_total").innerHTML = "Uploaded " + event.loaded + " bytes of " + event.total;
    var percent = (event.loaded / event.total) * 100;
    _getElementById("status").innerHTML = Math.round(percent) + "% uploaded... please wait";
}

function completeHandler(event) {
    document.location.reload();
}

function errorHandler(event) {
    _getElementById("status").innerHTML = "Upload Failed";
}

function abortHandler(event) {
    _getElementById("status").innerHTML = "Upload Aborted";
}