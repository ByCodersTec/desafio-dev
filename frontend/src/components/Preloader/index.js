import { Container, PreloaderContent, PreloaderContentInner } from "./styles";


const Preloader = () => {
    return <Container>
        <PreloaderContent>
            <PreloaderContentInner />
        </PreloaderContent>
    </Container>
}

export default Preloader;