import React, { useEffect, useRef, useState } from 'react';
import './FileInput.css'

const FileInput = () => {
    const inputRef = useRef(null);
    const [file, setFile] = useState({});
    
    const handleChange = function(e) {
      e.preventDefault();
      if (e.target.files && e.target.files[0]) {
        setFile(e.target.files[0]);
      }
    };
    
    const onButtonClick = () => {
      inputRef.current.click();
    };

    useEffect(() => {
      if (JSON.stringify(file) !== '{}')
        console.log(file);
    }, [file]);
    
    return (
      <form  className='form'  onSubmit={(e) => e.preventDefault()}>
        <input className='inputFile' ref={inputRef} type='file' multiple={false} onChange={handleChange} />
        <label className='label'>
          <div>
            <button className='uploadButton' onClick={onButtonClick}>Importar arquivo</button>
          </div> 
        </label>
      </form>
    );
  };

export default FileInput;