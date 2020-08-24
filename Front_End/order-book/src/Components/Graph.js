import React from 'react'
import { Container } from 'react-bootstrap'
import StockChart from './StockChart'

class Graph extends React.Component{

    render(){
        return(
            <Container>
                
                <StockChart/>
                
            </Container>
        )
    }
}

export default Graph