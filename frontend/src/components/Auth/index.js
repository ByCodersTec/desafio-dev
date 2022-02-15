import { Formik, Form } from 'formik';
import { connect } from 'react-redux';

import { useHistory } from 'react-router-dom';
import * as Yup from "yup";

import Preloader from '../Preloader';

import { Container, LoginFormContainer, FormGroup, FormSubmitButton } from './styles';
import { getLoginDispatch } from '../../store/modules/login/actions';

const Schema = Yup.object().shape({
    email: Yup.string().email("Provide a valid email").required("This field is required"),
    password: Yup.string().required("This field is required"),
});

const LoginPage = ({ loading, error, getLoginDispatch }) => {

    const history = useHistory();
    const initialValues = {email: "", password: ""};

    const handleSubmitForm = (values) => getLoginDispatch(values, history);

    return <Container>
        <LoginFormContainer>
            <Formik initialValues={initialValues} onSubmit={handleSubmitForm} validationSchema={Schema}>
                {({values, errors, handleBlur, handleChange}) => {
                    return <Form>
                        {error && <p className="error">
                            {error}
                            </p>}
                        {loading && <Preloader />}
                        <FormGroup>
                            <label>Email</label>
                            <input
                                type="text"
                                name="email"
                                placeholder="Email"
                                onBlur={handleBlur}
                                onChange={handleChange}
                                />
                            {errors.email && 
                                <span className="error"> {errors.email} </span>}
                        </FormGroup>
                        <FormGroup>
                            <label>Password</label>
                            <input
                                type="password"
                                name="password"
                                placeholder="&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;"
                                onBlur={handleBlur}
                                onChange={handleChange}
                                />
                            {errors.password && 
                                <span className="error"> {errors.password} </span>}
                        </FormGroup>
                        <FormSubmitButton type="submit">
                            Connect
                        </FormSubmitButton>
                    </Form>
                }}
            </Formik>
        </LoginFormContainer>
    </Container>
}

const mapStateToProps = (state) => {
    return { loading: state.login.loading, error: state.login.error}
};
  
export default connect(mapStateToProps, { getLoginDispatch })(LoginPage);
