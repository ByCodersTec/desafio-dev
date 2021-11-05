import React from "react";

const UploadForm = ({ fileChangeHandler, submitHandler, file }) => {
    return (
        <>
            <input
                type='file'
                onChange={(e) => fileChangeHandler(e)}
                name='file'
            />
            <button 
                disabled={file === ''}
                onClick={submitHandler}
            >
                Enviar lote de transações
            </button>
        </>
    )
}

export default UploadForm;