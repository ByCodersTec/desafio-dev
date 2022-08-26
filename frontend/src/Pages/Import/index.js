import React, {useState} from "react"
import Navigation from "../../Components/Navbar"
import Card from "../../Components/Card"
import backendUrl from "../../config.js";
import './styles.css'

const Import = () => {
    const [selectedFile, setSelectedFile] = useState();
	const [isSelected, setIsSelected] = useState(false);
	const [imported, setImported] = useState(false);

	const changeHandler = (event) => {
		setSelectedFile(event.target.files[0]);
		setIsSelected(true);
	};

    const handleSubmission = () => {
		const formData = new FormData();

		formData.append('file', selectedFile);

		fetch(backendUrl + 'transaction/import',
                {
                    method: 'POST',
                    body: formData,
                }
            )
			.then((response) => response.json())
			.then((result) => {
				if (result.data) {
                    setImported(true)
                }
			})
			.catch((error) => {
				console.error('Error:', error);
			});
	    };

    return (
        <div className="container">
                <Navigation />

                <div className="content">
                    <br />
                    <h1>Importar Lançamentos</h1>

                    <Card>
                        <h3>Importar</h3>
                        <br />

                        {!imported ? (
                            <div>
                                <input type="file" name="file" onChange={changeHandler} />

                                {isSelected ? (
                                    <div>
                                        <p>Nome: {selectedFile.name}</p>
                                        <p>Tipo: {selectedFile.type}</p>
                                        <p>Tamanho em bytes: {selectedFile.size}</p>
                                        <p>
                                            Modificado Em:{' '}
                                            {selectedFile.lastModifiedDate.toLocaleDateString()}
                                        </p>
                                    </div>
                                ) : (
                                    <p>Selecione o arquivo para ver os detalhes</p>
                                )}

                                <div>
                                    <button onClick={handleSubmission}>Enviar</button>
                                </div>
                            </div>
                            ) : (
                                <h3>Arquivo importado com sucesso</h3>
                            )}
                    </Card>
            </div>
        </div>
        
    )
}

export default Import;