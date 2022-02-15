import styled from "styled-components";

const ContainerShimmer = styled.div`

    display: flex;
    flex-direction: row;

    align-items: center;

    width: 100%;

    .user-image {
        width: 50px;
        height: 50px;
        border-radius: 50%;

        background-color: var(--tertiary);
    }

    .user-block {
        display: flex;
        flex-direction: column;
        
        align-items: center;

        width: 80%;

        margin-left: 7px;
        
        div:last-child {
            margin-top: 5px;
            height: 10px;
        }

        div {
            width: 100%;
            height: 14px;
            font-size: 13px;
            background-color: var(--tertiary);

            border-radius: 5px;
        }

    }

`;

const UserContentShimmer = () => {
    return <ContainerShimmer>
        <div className="user-image shimmer" />
        <div className="user-block">
            <div className="shimmer" />
            <div className="shimmer" />
        </div>
    </ContainerShimmer>;
}


export default UserContentShimmer;