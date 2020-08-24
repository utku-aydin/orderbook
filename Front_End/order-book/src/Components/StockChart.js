import React from 'react';
import { render } from 'react-dom';
import axios from 'axios';
import 'zingchart/es6';
import ZingChart from 'zingchart-react';
// EXPLICITLY IMPORT MODULE from node_modules
import "zingchart/modules-es6/zingchart-maps.min.js";
import "zingchart/modules-es6/zingchart-maps-usa.min.js";

class StockChart extends React.Component {
    constructor(props) {
      super(props);
      this.state = {
        orders: [],
        config:{
            type: 'bar',
            series: [{
              values: []
            }]
        }
          
       }
    }

    componentDidMount(){
        axios.get('http://localhost:8080/api/sellOrders')
        .then(res => this.setState({values: res.price}))
    }


    render() {
      return (
        <div>
          <ZingChart data={this.state.config}/>
        </div>
      );
    }
  }

  export default StockChart