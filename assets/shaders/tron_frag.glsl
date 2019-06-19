#version 330 core

//input from vertex shader
in struct VertexData
{
//3.1.3 Farbanpassung
    vec3 normale;
    vec3 position;
    vec2 tc;
} vertexData;
//Textures

uniform sampler2D texDiff;
uniform sampler2D texSpec;
uniform sampler2D texEmit;
uniform float shininess;

//fragment shader output
out vec4 color;


void main(){

    /*color = vec4(0, (0.5f + abs(vertexData.position.z)), 0, 1.0f);
    //3.1.3 Farbanpassung
    //color = vec4(abs(normalize(vertexData.normale)), 1.0f);
    //color=vec4(1.0f,0.0f,1.0f,1.0f);
    //color = texture2D(tex0,tc0) +  texture2D(tex1,tc1);*/
    color=texture(texEmit, vertexData.tc);


}
