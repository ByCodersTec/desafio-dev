import React, { useState, useEffect } from 'react';
import { FaUpload } from 'react-icons/fa';
import Container from '../../components/Container';
import { Form, SubmitButton, List } from './styles';

const Upload = (props) => {
 

    return (
      <Container>
        <h1>
          <FaUpload />
          Upload
        </h1>

      <Form style={{ paddingTop:'20px', textAlign:'center' }} action = "http://localhost:5000/transactions/upload" method = "POST" 
         encType = "multipart/form-data">
         <input type = "file" name = "file" />
         <input type = "submit"/>
      </Form>
       
      </Container>
    );
  
}

export default Upload;
