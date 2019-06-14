#version 330 core

layout(location = 0) in vec3 position;
layout(location = 2) in vec3 normale;

//uniforms
uniform mat4 model_matrix;
uniform mat4 view_matrix;
uniform mat4 proj_matrix;

out struct VertexData
{
//3.1.3 Farbanpassung
    vec3 normale;
    vec3 position;
} vertexData;

void main(){
    vec4 pos = proj_matrix*view_matrix*model_matrix * vec4(position, 1.0f);
    gl_Position = pos;
    vertexData.position = pos.xyz;
    //3.1.3 Farbanpassung
    vec4 nor = vec4(normale, 1.0f);
    vertexData.normale=(inverse(transpose(view_matrix*model_matrix))*nor).xyz;
}