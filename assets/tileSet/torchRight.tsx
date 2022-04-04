<?xml version="1.0" encoding="UTF-8"?>
<tileset version="1.8" tiledversion="1.8.4" name="torch" tilewidth="64" tileheight="128" tilecount="3" columns="0">
 <tileoffset x="64" y="-32"/>
 <grid orientation="orthogonal" width="1" height="1"/>
 <tile id="0">
  <image width="64" height="128" source="../tiles/doodads/torch_3.png"/>
 </tile>
 <tile id="1">
  <image width="64" height="128" source="../tiles/doodads/torch_2.png"/>
 </tile>
 <tile id="2">
  <image width="64" height="128" source="../tiles/doodads/torch_1.png"/>
  <animation>
   <frame tileid="2" duration="150"/>
   <frame tileid="1" duration="150"/>
   <frame tileid="0" duration="150"/>
  </animation>
 </tile>
</tileset>
