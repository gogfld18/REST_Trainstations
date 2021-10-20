function getAllTrains(){
    fetch('./api/train')
        .then(response => response.json())
        .then(
            data => {
                let html = "<table>";

                data.forEach(d => html += `<tr><td>${d.id}</td><td>${d.type}</td></tr>`)

                html += "</table>";

                document.getElementById("allTrains").innerHTML = html;
            }
        );
}

function getStations(id){
    fetch('./api/train/' + id.value)
        .then(response => {
            console.log(response);
            if(response.status != 200){
                alert("Invalid Request " + response.status + " " + response.statusText);
            }
            return response.json();
        })
        .then(
            d => {
                let html = "<table>";

                d.stations.forEach(s => html += `<tr><td>${s}</td></tr>`);

                html += "</table>";

                document.getElementById("stations").innerHTML = html;
            }
        );
}

function addTrain(id,type){
    let data = {
        id: id.value,
        type: type.value
    }

    fetch('./api/train',{
        method: "POST",
        headers: {
            "Content-Type" : "application/json"
        },
        body: JSON.stringify(data)
    }).then(response => {
        console.log(response.status);
        if(response.status == 201) //created
            alert(response.headers.get("Location"));
        else
            alert("Invalid Request " + response.status + " " + response.statusText);
    });
}

function addStation(id, station){



    fetch('./api/train/' + id.value,{
        method: "POST",
        headers: {
            "Content-Type" : "application/json"
        },
        body: station.value
    }).then(response => {
        console.log(response.status);
        if(response.status == 201) //created
            alert(response.headers.get("Location"));
        else
            alert("Invalid Request " + response.status + " " + response.statusText);
    });
}

