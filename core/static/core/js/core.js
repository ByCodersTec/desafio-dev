//requests
function request (url, settings) {
    url = new URL(url)
    return fetch(url, settings)
}

const submit_form = async () => {
    
    const validation = get_validation()
    if ( !validation.is_valid ){
        get_swal_alert(["Atenção !", "Revise o arquivo fornecido.", "info"])
        return
    }

    const settings = {
        "method": "POST",
        "headers": {
            "X-CSRFToken": get_cookie('csrftoken'),
        },
        "body": validation["form"]
    }
    const location = `${window.location.protocol}${window.location.host}`
    const url = `${location}/core/api/importador/cnab/`
    
    const response_code = {
        200: success,
        404: not_found,
        422: error_parameters,
        500: error_internal
    }

    try {
        const response = await request(url, settings)
        const dados = await response.json()
        response_code[response.status](dados, monta_grid_lancamentos)
    } catch (e){
        console.log(e)
        swal.close()
        get_swal_alert(["Erro !", "Ocorreu um erro inesperado.", "error"])
    }
}


//alerts
const get_swal_alert = ([title, text, icon] = par) => {
    Swal.fire({
        title: title,
        text: text,
        icon: icon,
    })
}

const success = (dados, metodo) => {
    metodo(dados)
}

const not_found = () => {
    get_swal_alert(["Atenção !", "Nada Encontrado.", "warning"])
}

const error_parameters = () => {
    get_swal_alert(["Atenção !", "Parâmetros passados de forma incorreta.", "info"])
}

const error_internal = () => {
    get_swal_alert(["Erro !", "Ocorreu um erro interno no servidor.", "error"])
}

const error_function_dependency = () => {
    get_swal_alert(["Erro !", "O Processamento não foi realizado, pois uma função interna falhou.", "error"])
}

//validation 

const get_validation = () => {
    let is_valid = false
    let form_data
    const file = document.getElementById("file")
    if(file.files.length > 0){
        is_valid = true
        form_data = new FormData(document.getElementById("form_arquivo"))
    }

    return { is_valid, form_data }
}

const get_cookie = (cname) => {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}