import React from 'react'
import { Container, Form, Button } from 'react-bootstrap'
import StockChart from './StockChart'

class Graph extends React.Component{

    render(){
        let {graphData, handleGraphDataChange, handleGraphDataSubmit} = this.props;
        return(
            
            <Form onSubmit={handleGraphDataSubmit}>
                <Form.Group>
                    <Form.Label>Interval:  </Form.Label>
                    <input type="number" id="interval" name="interval" step="1"
                    value = {graphData.interval} onChange={handleGraphDataChange}></input>
                    <Form.Label>Count: </Form.Label>
                    <input type="number" id="count" name="count" step="0.01"
                    value = {graphData.count} onChange={handleGraphDataChange}></input>
                    <Button variant="primary" type="submit">
                        Submit
                    </Button>
                </Form.Group>
                
                <StockChart 
                orders = {this.props.graphData}/>
            </Form>
                
                
            
        )
    }
}

export default Graph