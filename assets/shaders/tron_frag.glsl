#version 330 core

//input from vertex shader
in struct VertexData
{
//3.1.3 Farbanpassung
    vec3 normale;
    vec3 position;
    vec2 tc;
    vec3 pos;
} vertexData;
//Textures

uniform sampler2D texDiff;
uniform sampler2D texSpec;
uniform sampler2D texEmit;
uniform float shininess;
uniform vec3 lightColor;
uniform vec3 lightPos;

//fragment shader output
out vec4 color;


void main(){
    float ambientStrength=0.1f;
    vec3 ambient= ambientStrength* lightColor;

    vec3 norm= normalize(vertexData.normale);
    vec3 lightDir= normalize(lightPos-vertexData.pos);
    float diff=max(dot(norm, lightDir),0.0f);
    vec3 diffuse=diff*lightColor;
    vec3 result= ambient+diffuse;
    color=vec4(result, 1.0f)*texture(texEmit, vertexData.tc);
    //TODO Light bis jetzt nur bis Diffuse implementiert


}
