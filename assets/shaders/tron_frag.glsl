#version 330 core

//input from vertex shader
in struct VertexData
{
//3.1.3 Farbanpassung
    vec3 normale;
    vec3 position;
    vec2 tc;
    vec3 pos;
    vec3 toLight;
    vec3 toSpot;
    vec3 toCamera;
} vertexData;
//Textures

uniform sampler2D texDiff;
uniform sampler2D texSpec;
uniform sampler2D texEmit;
uniform vec3 ambientCol;
uniform float shininess;
uniform vec3 lightColor;
uniform vec3 spotColor;
uniform vec3 viewPos;
uniform float intensity;
uniform float cutOff;
uniform float outerCutOff;
uniform float phi;
uniform float kC;
uniform float kL;
uniform float kQ;
uniform vec3 direction;

//fragment shader output
out vec4 color;


void main(){


    float theta = dot(normalize(vertexData.toSpot), normalize(-direction));
    //if(theta>cutOff){
        float epsilon = (cutOff - outerCutOff);
        //vec3 n = normalize(vertexData.normale);
        // float intens = max(dot(n,normalize(vertexData.toSpot)), 0.0);
        float intens = clamp((theta - outerCutOff) / epsilon, 0.0, 1.0);


        float distance= length(vertexData.toSpot-vertexData.position);
        float attenuation= 1.0/(kC+kL*distance+kQ*(distance*distance));

        vec3 norm= normalize(vertexData.normale);
        vec3 lightDir= normalize(vertexData.toSpot-vertexData.toCamera);

        float cosa=max(0.0f, dot(norm, lightDir));
        vec3 DiffuseTerm = texture(texDiff, vertexData.tc).xyz * spotColor;
        DiffuseTerm *= intens;
        DiffuseTerm*=attenuation;
        //vec3 diffuse=cosa*lightColor;

        color = vec4(DiffuseTerm * cosa, 1.0);

        vec3 ambientTerm=texture(texEmit, vertexData.tc).xyz*ambientCol;
        color += vec4(ambientTerm, 0.0);

        vec3 viewDir = normalize(vertexData.toCamera);
        vec3 R = normalize(reflect(-lightDir, norm));
        float cosBeta = max(0.0, dot(R, viewDir));
        float cosBetak = pow(cosBeta, shininess);

        vec3 specularTerm = texture(texSpec, vertexData.tc).xyz * spotColor;
        specularTerm*=intens;
        specularTerm*=attenuation;
        color += vec4(specularTerm * cosBetak, 0.0);
    //}else{
       distance= length(vertexData.toLight-vertexData.position);
        attenuation= 1.0/(kC+kL*distance+kQ*(distance*distance));

        norm= normalize(vertexData.normale);
        lightDir= normalize(vertexData.toLight-vertexData.toCamera);

        cosa=max(0.0f, dot(norm, lightDir));
       DiffuseTerm = texture(texDiff, vertexData.tc).xyz * lightColor;
        DiffuseTerm*=attenuation;
        //vec3 diffuse=cosa*lightColor;

        color += vec4(DiffuseTerm * cosa, 1.0);

        ambientTerm=texture(texEmit, vertexData.tc).xyz*ambientCol;
        color += vec4(ambientTerm, 0.0);

         viewDir = normalize(vertexData.toCamera);
         R = normalize(reflect(-lightDir, norm));
        cosBeta = max(0.0, dot(R, viewDir));
         cosBetak = pow(cosBeta, shininess);

         specularTerm = texture(texSpec, vertexData.tc).xyz * lightColor;
        specularTerm*=attenuation;
        color += vec4(specularTerm * cosBetak, 0.0);

    }
/*
    float specularStrength=0.5f;

    vec3 reflectDir = reflect(-lightDir, norm);
    float spec = pow(max(dot(viewDir, reflectDir), 0.0), 32);
    vec3 specular = specularStrength * spec * lightColor;

    vec3 result= ambient+diffuse+specular;
    vec4 result2=(texture(texDiff, vertexData.tc)*vec4(lightColor, 1.0f))+texture(texSpec,vertexData.tc);
    //color=vec4(result, 1.0f)*texture(texEmit, vertexData.tc);
    color=result2*texture(texEmit, vertexData.tc);

*/


//}
