import React, { useState, useEffect } from 'react';
import Select from "react-select";
import { FaGithubAlt, FaPlus, FaSpinner } from 'react-icons/fa';
import { Link } from 'react-router-dom';
import api from '../../services/api';
import Table from 'react-bootstrap/Table'

import Container from '../../components/Container';
import { Form, SubmitButton, List } from './styles';

// import { Container } from './styles.css';

const Upload = (props) => {
 

    return (
      <Container>
        <h1>
          <FaGithubAlt />
          Upload
        </h1>

      <form action = "http://localhost:5000/uploader" method = "POST" 
         encType = "multipart/form-data">
         <input type = "file" name = "file" />
         <input type = "submit"/>
      </form>
       
      </Container>
    );
  
}

export default Upload;
