import React, { useEffect, useState } from 'react';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import { styled } from '@mui/material/styles';
import Button from '@mui/material/Button';
import api from '../../service/api'
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import TablePagination from '@mui/material/TablePagination';
import moment from 'moment'

const StyledTableCell = styled(TableCell)(({ theme }) => ({
    [`&.${tableCellClasses.head}`]: {
        backgroundColor: theme.palette.common.white,
        color: theme.palette.common.black,
    },
    [`&.${tableCellClasses.body}`]: {
        fontSize: 14,
    },
}));

const StyledTableRow = styled(TableRow)(({ theme }) => ({
    '&:nth-of-type(odd)': {
        backgroundColor: theme.palette.action.hover,
    },
    '&:last-child td, &:last-child th': {
        border: 0,
    },
}));

const Input = styled('input')({
    display: 'none',
});

function Home() {
    const [selected, setSelected] = useState();
    const [result, setResult] = useState();
    const [uploaded, setUploaded] = useState(false)
    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(10);

    const handleChangePage = (event, newPage) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (event) => {
        setRowsPerPage(+event.target.value);
        setPage(0);
    };
    const selectFile = (event) => {
        setSelected(event.target.files[0]);
    };

    useEffect(() => {
        if (uploaded) {
            getValues()
        }
    }, [uploaded]);

    const handleUploadFile = async () => {
        if (typeof selected === "undefined") {
            alert("Selecione um arquivo!")
            return
        }
        let file = new FormData();
        file.append("file", selected);
        file.append("fileName", selected.name);

        await api.post("upload", file)
            .then(res => {
                file = new FormData()
                setUploaded(true)
               // setSelected()
               console.log(res.data)
            })
            .catch(err => {
                console.log(err)
            })
    };


    const getValues = async () => {
        await api.get('upload')
            .then(res => {
                setResult(res.data.content)
            })
    }

    let entrada = []
    let valorEntrada
    let valorSaida
    let saida = []
    const valores = (value) => {
        if (value.TipoTransacao.natureza === 'Entrada') {
            entrada.push(value.valor)
        } else {
            saida.push(value.valor)
        }
    }

    if (result) {
        if (result.length > 0) {
            result.filter(valores);
            let sum = a => a.reduce((x, y) => x + y);
            valorEntrada = sum(entrada.map(x => Number(x)))
            valorSaida = sum(saida.map(x => Number(x)))
        }
    }

    return (
        <>
            <Container maxWidth="sm">
                <Box sx={{ bgcolor: '#f2f0f03b', height: 'auto' }}>
                    <Paper sx={{ boxShadow: "none", bgcolor: '#f2f0f03b' }} style={{ padding: 10, textAlign: 'center', fontSize: 30, fontFamily: ' "Trebuchet MS", Helvetica, sans-serif' }}>Selecione o arquivo para upload!</Paper>
                    <label htmlFor="contained-button-file">
                        <Input accept=".txt" id="contained-button-file" type="file" onChange={selectFile} />
                        <Button color='success' style={{ padding: 10, width: '100%' }} variant="outlined" component="span">
                            Selecione
                        </Button>
                    </label>
                    {typeof selected !== "undefined" ? (<Paper sx={{ boxShadow: "none", bgcolor: '#f2f0f03b' }} style={{ marginTop: 20, marginBottom: 20 }}>{selected.name}</Paper>) : <Paper sx={{ boxShadow: "none", bgcolor: '#f2f0f03b' }} style={{ marginTop: 20, marginBottom: 20 }}>Nenhum arquivo selecionado</Paper>}
                    <Button style={{ marginTop: 20, marginBottom: 20, width: '100%' }} variant="contained" component="span" color='success' onClick={handleUploadFile}>Upload</Button>
                </Box>
                {result ? result.length > 0 ? (
                        <Box sx={{ bgcolor: '#f2f0f03b', height: 'auto' }}>
                            <Paper sx={{ boxShadow: "none", bgcolor: '#f2f0f03b' }}
                                style={{ padding: 10, textAlign: 'center', fontSize: 20, fontFamily: ' "Trebuchet MS", Helvetica, sans-serif' }}>
                                Entradas: {parseFloat(valorEntrada).toFixed(2)}
                            </Paper>
                            <Paper sx={{ boxShadow: "none", bgcolor: '#f2f0f03b' }}
                                style={{ padding: 10, textAlign: 'center', fontSize: 20, fontFamily: ' "Trebuchet MS", Helvetica, sans-serif' }}>
                                Saídas: {parseFloat(valorSaida).toFixed(2)}
                            </Paper>
                            <Paper sx={{ boxShadow: "none", bgcolor: '#f2f0f03b' }}
                                style={{ padding: 10, textAlign: 'center', fontSize: 20, fontFamily: ' "Trebuchet MS", Helvetica, sans-serif' }}>
                                Total: {parseFloat(valorEntrada).toFixed(2) - parseFloat(valorSaida).toFixed(2)}
                            </Paper>
                        </Box>
                ) : null : null}
            </Container>

            <TableContainer component={Paper}>
                <Table sx={{ minWidth: 700 }} aria-label="customized table">
                    <TableHead style={{backgroundColor:'red'}}>
                        <TableRow>
                            <StyledTableCell>Tipo Transação</StyledTableCell>
                            <StyledTableCell>Natureza</StyledTableCell>
                            <StyledTableCell>Sinal</StyledTableCell>
                            <StyledTableCell>Data Transação</StyledTableCell>
                            <StyledTableCell align="right">Valor</StyledTableCell>
                            <StyledTableCell align="right">CPF</StyledTableCell>
                            <StyledTableCell align="right">Cartão</StyledTableCell>
                            <StyledTableCell align="right">Hora</StyledTableCell>
                            <StyledTableCell align="right">Dono da Loja</StyledTableCell>
                            <StyledTableCell align="right">Nome da Loja</StyledTableCell>
                            <StyledTableCell align="right">Data do Upload</StyledTableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {result ? result.length > 0 ?
                            result.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                                .map((row, i) => (
                                    <StyledTableRow key={i}>
                                        <StyledTableCell component="th" scope="row">{row.TipoTransacao.descricao}</StyledTableCell>
                                        <StyledTableCell component="th" scope="row">{row.TipoTransacao.natureza}</StyledTableCell>
                                        <StyledTableCell component="th" scope="row">{row.TipoTransacao.sinal}</StyledTableCell>
                                        <StyledTableCell component="th" scope="row">{moment(row.data).format('DD-MM-YYYY')}</StyledTableCell>
                                        <StyledTableCell align="right">{row.valor}</StyledTableCell>
                                        <StyledTableCell align="right">{row.cpf}</StyledTableCell>
                                        <StyledTableCell align="right">{row.cartao}</StyledTableCell>
                                        <StyledTableCell align="right">{`${row.hora.substring(0, 2)}:${row.hora.substring(2, 4)}:${row.hora.substring(4, 6)}`}</StyledTableCell>
                                        <StyledTableCell align="right">{row.donoLoja}</StyledTableCell>
                                        <StyledTableCell align="right">{row.nomeLoja}</StyledTableCell>
                                        <StyledTableCell align="right">{moment(row.dataUpload).format('DD-MM-YYYY')}</StyledTableCell>
                                    </StyledTableRow>
                                )) : null : null
                        }
                    </TableBody>
                </Table>
            </TableContainer>
            <TablePagination
                rowsPerPageOptions={[10, 25, 100]}
                component="div"
                count={typeof result !== "undefined" ? result.length > 0 ? result.length : 0 : 0}
                rowsPerPage={rowsPerPage}
                page={page}
                onPageChange={handleChangePage}
                onRowsPerPageChange={handleChangeRowsPerPage}
            />
        </>
    )
}

export default Home