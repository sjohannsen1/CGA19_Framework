#version 330 core

layout(location = 0) in vec3 position;
layout(location = 2) in vec3 normale;
layout (location=1) in vec2 tc0;

//uniforms
uniform mat4 model_matrix;
uniform mat4 view_matrix;
uniform mat4 proj_matrix;
uniform vec2 tcMultiplier;
uniform vec3 lightPos;
//TODO: 5.3.3


out struct VertexData
{
//3.1.3 Farbanpassung
    vec3 normale;
    vec3 position;
    vec2 tc;
    vec3 pos;
    vec3 toLight;
    vec3 toCamera;
} vertexData;

void main(){
    /*vec4 worldPos=model_matrix * vec4(position, 1.0f);
    vec4 pos = proj_matrix*view_matrix*worldPos;
    gl_Position = pos;
    //vertexData.pos=vec3(model_matrix*vec4(position, 1.0f));
    vertexData.position = pos.xyz;
    vertexData.toLight=lightPos-worldPos.xyz;
    vertexData.toCamera=-(view * model * v).xyz;//(inverse(view_matrix)*vec4(0.0f,0.0f,0.0f,1.0f).xyz-worldPos.xyz);*/
    vec4 v= vec4(position, 1.0);
    gl_Position = proj_matrix * view_matrix * model_matrix * v;
    // compute normal in view space //
    vec4 n= vec4(normale, 0.0);
    mat4 normalMat = transpose(inverse(view_matrix * model_matrix));
    vertexData.normale = (normalMat * n).xyz;
    // compute light direction in view space //
    vec4 lp = view_matrix * vec4(lightPos, 1.0);
    vec4 P = (view_matrix * model_matrix * v);
    vertexData.toLight = (lp - P).xyz;

    //3.1.3 Farbanpassung
    vertexData.tc=tc0*tcMultiplier;
}