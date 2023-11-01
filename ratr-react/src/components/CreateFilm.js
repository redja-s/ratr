import React, { Component } from 'react';
import axios from 'axios';

class CreateFilm extends Component {
  state = {
    film: {
      title: '',
      director_name: '',
      release_year: '',
    },
    message: '',
  };

  handleInputChange = (event) => {
    const { name, value } = event.target;
    this.setState({
      film: {
        ...this.state.film,
        [name]: value,
      },
    });
  };

  handleFormSubmit = (event) => {
    event.preventDefault();

    console.log(this.state.film)

    // Make a POST request to your API
    axios.post('http://localhost:8080/films', this.state.film, {
        headers: {
            'Content-Type': 'application/json'
        }
    })
      .then((response) => {
        this.setState({ message: 'Film created successfully' });
        window.location.reload();
      })
      .catch((error) => {
        console.log(error)
        this.setState({ message: 'Error creating film' });
      });
  };

  render() {
    return (
      <div>
        <h2 className="text-9xl te-">Create Film</h2>
        <form onSubmit={this.handleFormSubmit}>
          <div>
            <label>Title:</label>
            <input
              type="text"
              name="title"
              value={this.state.film.title}
              onChange={this.handleInputChange}
            />
          </div>
          <div>
            <label>Director:</label>
            <input
              type="text"
              name="director_name"
              value={this.state.film.director_name}
              onChange={this.handleInputChange}
            />
          </div>
          <div>
            <label>Release Year:</label>
            <input
              type="text"
              name="release_year"
              value={this.state.film.release_year}
              onChange={this.handleInputChange}
            />
          </div>
          <button type="submit">Create Film</button>
        </form>
        {this.state.message && <p>{this.state.message}</p>}
      </div>
    );
  }
}

export default CreateFilm;
