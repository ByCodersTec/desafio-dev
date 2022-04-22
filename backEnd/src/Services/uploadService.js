import Return from '../Models/Return';
import UploadRepository from '../repo/uploadRepo'

class UploadService {
    async upload() {
        let response
        var fs = require('fs'),
            path = require('path'),
            filePath = path.join(__dirname, '../files/CNAB.txt')
        let array = []
        fs.readFile(filePath, { encoding: 'utf-8' }, async function (err, data) {
            if (!err) {
                data = data.split(/\r?\n/)

                data.map((row) => {
                    const tipoTransacao = row.substring(0, 1)
                    const data = row.substring(1, 9)
                    const valor = row.substring(9, 19)
                    const v = valor / 100.00
                    const cpf = row.substring(19, 30)
                    const cartao = row.substring(30, 42)
                    const hora = row.substring(42, 48)
                    const donoLoja = row.substring(48, 62)
                    const nomeLoja = row.substring(62, 81)
                    let obj = {
                        tipoTransacao: parseInt(tipoTransacao),
                        data: data,
                        valor: parseFloat(v),
                        cpf: cpf,
                        cartao: cartao,
                        hora: hora,
                        donoLoja: donoLoja,
                        nomeLoja: nomeLoja
                    }
                    array.push(obj)
                })


            } else {
                console.log(err);
                response = err
            }
            const up = array.map(async (r) => {
                response = await UploadRepository.upload(r)
            })
            var filePath1 = path.join(__dirname, '../files/')
            fs.readdir(filePath1, (err, files) => {
                if (err) throw err;

                for (const file of files) {
                    fs.unlink(path.join(filePath1, file), err => {
                        if (err) throw err;
                    });
                }
            });
            return response
        });
    }

    async getAll() {
        return await UploadRepository.getAll()
    }
}

export default new UploadService()