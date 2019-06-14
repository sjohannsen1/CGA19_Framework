#version 330 core

//input from vertex shader
in struct VertexData
{
//3.1.3 Farbanpassung
    vec3 normale;
    vec3 position;
} vertexData;


//fragment shader output
out vec4 color;


void main(){

    //color = vec4(0, (0.5f + abs(vertexData.position.z)), 0, 1.0f);
    //3.1.3 Farbanpassung
    color = vec4(abs(normalize(vertexData.normale)), 1.0f);
    //color=vec4(1.0f,0.0f,1.0f,1.0f);


}