<template>
  <div class="connection">
    <h1>Project {{projectID}}</h1>
  </div>
</template>

<script>

  import {dataBus} from "../main";

  /**
   * This component deals with sending requests to and receiving responses from the REST API.
   * Upon creation, it starts listening to events transmitted over the {@link dataBus}.
   * After sending a GET or POST request, it sends the response over the dataBus via a "response" event.
   * Note that any event that sends a POST request needs to include the request body.
   *
   * props: baseUrl - The baseURL of the REST API
   */
  export default {

    name: "Connection",

    props: {
      baseUrl: String
    },

    data() {
      return {
        projectID: ""
      }
    },

    methods: {

      createProject: function() {
        console.log("GET REQUEST TO URL=\"" + this.baseUrl + "/create\":");
        this.$http.get(this.baseUrl + "/create")
          .then(response => {
            console.log("DATA RECEIVED:");
            console.log(response);
            if (response.body.hasOwnProperty("canvas")) {
              this.projectID = response.body.projectID;
              dataBus.$emit('response', response);
            }
          })
      },

      loadProject: function(id) {
        console.log("GET REQUEST TO URL=\"" + this.baseUrl + "/" + id + "\":");
        this.$http.get(this.baseUrl + "/" + id)
          .then(response => {
            console.log("DATA RECEIVED:");
            console.log(response);
            if (response.body.hasOwnProperty("canvas")) {
              this.projectID = response.body.projectID;
              dataBus.$emit('response', response);
            }
          })
      },

      postRequest: function(requestType, data) {
        console.log("POST REQUEST TO URL=\"" + this.baseUrl + "/" + this.projectID + "/" +  requestType + "\":");
        console.log("SENDING DATA:");
        console.log(data);
        this.$http.post(this.baseUrl + "/" + this.projectID + "/" +  requestType, data)
          .then(response => {
            console.log("DATA RECEIVED:");
            console.log(response);
            if (response.body.hasOwnProperty("canvas")) {
              this.projectID = response.body.projectID;
              dataBus.$emit('response', response);
            }
          })
      },

      downloadRequest: function(fileType) {
        console.log("OPENING URL=\"/" + this.projectID + "/download/" + fileType + "\":");
        window.open(this.baseUrl + "/" + this.projectID + "/download/" + fileType);
      }

    },

    created: function () {
      dataBus.$on('createProject', () => this.createProject());
      dataBus.$on('loadProject', (id) => this.loadProject(id));
      dataBus.$on('editProject', (data) => this.postRequest('editProject', data));
      dataBus.$on('addShape', (data) => this.postRequest('addShape', data));
      dataBus.$on('addLayerGroup', (data) => this.postRequest('addLayerGroup', data));
      dataBus.$on('modifyShape', (data) => this.postRequest('modifyShape', data));
      dataBus.$on('editElement', (data) => this.postRequest('editElement', data));
      dataBus.$on('transformElement', (data) => this.postRequest('transformElement', data));
      dataBus.$on('removeElement', (data) => this.postRequest('removeElement', data));
      dataBus.$on('download', (fileType) => this.downloadRequest(fileType));
      this.createProject();
    }

  }

</script>

<style scoped>

  .connection {
    display: none;
  }

</style>
