function solution(clothes) {
    var map = {};
    for (var i = 0; i < clothes.length; i++) {
        var k = clothes[i][1];
        map[k] = (map[k] || 1) + 1;
    }
    console.log(map)
    var re = 1;
    for(var key in map){
        re *= map[key];
    }

    return re - 1;
}