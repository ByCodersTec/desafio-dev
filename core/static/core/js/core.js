// manipulando elementos

const get_total_loja = (lancamento, proximo_lancamento, dados, listagem) => {
    let insere_total = false
    let nome_loja = ""
    let count_total = 0.00
    let positivo_negativo = 1

    try {
        trata_undefined = proximo_lancamento.nome_loja
    } catch (error) {
        trata_undefined = ""
    }

    if(trata_undefined != lancamento.nome_loja ){
        insere_total = true
    }
    
    if (insere_total) {
        let tr_total = document.createElement("tr")
        let td_total = document.createElement("td")

        let td_suporte = document.createElement("td")
        td_suporte.setAttribute("colspan", 6)

        let td_titulo = document.createElement("td")
        td_titulo.innerText = "Total"

        nome_loja = lancamento.nome_loja
        lancamentos_loja = dados.filter( ( lancamento_filter ) => lancamento_filter.nome_loja === nome_loja)
        for ( lancamento_loja of lancamentos_loja ){
            positivo_negativo = lancamento_loja.tipo_transacao.sinal === "-" ? + 1 : -1
            count_total = count_total + ( parseFloat(lancamento_loja.valor) * positivo_negativo)
        }

        td_total.innerText = count_total.toFixed(2)

        tr_total.appendChild(td_suporte)
        tr_total.appendChild(td_titulo)
        tr_total.appendChild(td_total)
        listagem.appendChild(tr_total)
        
    }

}

const create_row = (lancamento, listagem) => {
    
    let tr = document.createElement("tr")

    let td_tipo = document.createElement("td")
    td_tipo.innerText = lancamento.tipo_transacao.descricao

    let td_data = document.createElement("td")
    td_data.innerText = lancamento.data_ocorrencia

    let td_valor = document.createElement("td")
    td_valor.innerText = lancamento.valor

    let td_cpf = document.createElement("td")
    td_cpf.innerText = lancamento.cpf

    let td_cartao = document.createElement("td")
    td_cartao.innerText = lancamento.cartao

    let td_hora = document.createElement("td")
    td_hora.innerText = lancamento.hora

    let td_dono_loja = document.createElement("td")
    td_dono_loja.innerText = lancamento.dono_loja

    let td_nome_loja = document.createElement("td")
    td_nome_loja.innerText = lancamento.nome_loja

    tr.appendChild(td_tipo)
    tr.appendChild(td_data)
    tr.appendChild(td_cpf)
    tr.appendChild(td_cartao)
    tr.appendChild(td_hora)
    tr.appendChild(td_dono_loja)
    tr.appendChild(td_nome_loja)
    tr.appendChild(td_valor)
    listagem.appendChild(tr)
    
}

const ordenar = (lancamento_a, lancamento_b) => {
    if (lancamento_a.nome_loja > lancamento_b.nome_loja) {
      return 1;
    }
    if (lancamento_a.nome_loja < lancamento_b.nome_loja) {
      return -1;
    }
    return 0;
  }

const create_grid = (dados) => {
    document.getElementById("div-tabela").classList.remove("hidden")
    dados.sort(ordenar)
    const qtd_total = dados.length
    let key = 0;
    const listagem = document.getElementById("listagem-lancamentos")

    for ( lancamento of dados ){
        key = key <= qtd_total ? key + 1 : key 
        create_row(lancamento, listagem)
        get_total_loja(lancamento, dados[key], dados, listagem)
    }
}

const click_input_arquivo = () => {
    document.getElementById("file").click()
}

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
    
    const location = `${window.location.protocol}${window.location.host}`
    const url = `${location}/api/importador/cnab/`
    const form_data = new FormData(document.getElementById("form-arquivo"))
    
    const settings = {
        "method": "POST",
        "headers": {
            "X-CSRFToken": get_cookie('csrftoken'),
        },
        "body": form_data
    }
    alerta_processando()
    
    const response_code = {
        200: success,
        404: not_found,
        422: error_parameters,
        500: error_internal,
        424: error_function_dependency
    }

    try {
        const response = await request(url, settings)
        const dados = await response.json()
        document.getElementById("file").value = ""
        swal.close()
        response_code[response.status](dados, create_grid)
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


const alerta_processando = () => {
    Swal.fire({
        title: 'Processando!',
        icon : "info",
        html: 'extraindo informações..',
            didOpen: () => {
                swal.showLoading()
            }
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
    const file = document.getElementById("file")
    if(file.files.length > 0){
        is_valid = true
    }

    return { is_valid }
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


//ações iniciais
const acoes_iniciais = () => {
    document.getElementById("btn-importador").addEventListener("click", click_input_arquivo)
}

window.addEventListener("load", acoes_iniciais)

