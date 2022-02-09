function solution(genres, plays) {
    var map = {};
    for(var i = 0; i < genres.length; i++){
        if(map[genres[i]] === undefined)
            map[genres[i]] = [];
        map[genres[i]].push([i, plays[i]]);
        // map[genres[i]] = map[genres[i]] ? map[genres[i]].push([i, plays[i]]) : [[i, plays[i]]];
    }

    var mostPlayOfGenre = [];
    for(var k in map){
        var v = map[k];
        var sum = 0;
        for(var j in v){
            sum += v[j][1];
        }
        v.sort((a, b) => b[1] === a[1] ? a[0] - b[0] : b[1] - a[1]);
        var temp = [sum, v[0]];
        if(v.length > 1){
            temp.push(v[1]);
        }
        mostPlayOfGenre.push(temp);
    }

    mostPlayOfGenre.sort((a, b) => b[0] - a[0]);
    var re = [];
    for(var i in mostPlayOfGenre){
        var songs = mostPlayOfGenre[i];
        for(var j = 1; j < songs.length; j++){
            var song = songs[j];
            re.push(song[0]);
        }
    }
    return re;
}