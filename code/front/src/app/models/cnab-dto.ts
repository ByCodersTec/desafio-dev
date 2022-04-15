import { TipoTransacaoDTO } from "./tipo-transacao-dto";

export class CnabDTO {
    tipo: TipoTransacaoDTO;
    dataHoraTransacao:string;
    valor:number;
    cpf:string;
    numeroCartao:string;
    representanteLoja:string;
    nomeLoja:string;
    
    constructor(){}
}