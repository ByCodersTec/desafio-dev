import React, { useEffect, useRef, useState } from 'react';
import databaseService from '../service/database.service'
import './FileInput.css'

const FileInput = () => {
    const inputRef = useRef(null);
    const [file, setFile] = useState(null);
    
    const handleChange = function(e) {
      e.preventDefault();
      if (e.target.files && e.target.files[0]) {
        const file = e.target.files[0];
        setFile(file);
      }
    };
    
    const onButtonClick = () => {
      inputRef.current.click();
    };

    const saveFile = async () => {
      const data = new FormData();
      data.append("file", file);
    
      databaseService.saveCNAB(data).then(() => {
        setFile(null);
      });
    };

    useEffect(() => {
      if (file) saveFile();
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [file]);
    
    return (
      <form  className='form'  onSubmit={(e) => e.preventDefault()}>
        <input className='inputFile' ref={inputRef} type='file' multiple={false} accept='.txt' onChange={handleChange} />
        <label className='label'>
          <div>
            <button className='uploadButton' onClick={onButtonClick}>Importar arquivo</button>
          </div> 
        </label>
      </form>
    );
  };

export default FileInput;